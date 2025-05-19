from Command import KotlinCommand, PythonCommand, BashCommand, JavaCommand


class Handlers:
    def set_next(self, handler):
        pass

    def check_file(self, file_path):
        pass


class HandleKotlinFile(Handlers):
    _next_handler = None

    def set_next(self, handler):
        self._next_handler = handler

    def check_file(self, file_path):
        try:
            file = open(file_path, "r")
            content = file.read()
            if any(key in content for key in ["fun ", "val ", "var ", "when "]):
                KotlinCommand().execute(file_path)
                return True
        except:
            print("ERROR: File not found or cannot be read.")
            return False

        if self._next_handler:
            return self._next_handler.check_file(file_path)
        return False

    @property
    def next_handler(self):
        return self._next_handler


class HandlePythonFile(Handlers):
    _next_handler = None

    def set_next(self, handler):
        self._next_handler = handler

    def check_file(self, file_path):
        try:
            file = open(file_path, "r")
            content = file.read()
            if "if __name__ == '__main__':" in content or "def " in content:
                PythonCommand().execute(file_path)
                return True
        except:
            print("ERROR: File not found or cannot be read.")
            return False

        if self._next_handler:
            return self._next_handler.check_file(file_path)
        return False


class HandleBashFile(Handlers):
    _next_handler = None

    def set_next(self, handler):
        self._next_handler = handler

    def check_file(self, file_path):
        try:
            file = open(file_path, "r")
            content = file.read()
            if any(key in content for key in ["#!/bin/bash", "#!/bin/sh", "echo", "mkdir", "cd", "pwd"]):
                BashCommand().execute(file_path)
                return True
        except:
            print("ERROR: File not found or cannot be read.")
            return False

        if self._next_handler:
            return self._next_handler.check_file(file_path)
        return False


class HandleJavaFile(Handlers):
    _next_handler = None

    def set_next(self, handler):
        self._next_handler = handler

    def check_file(self, file_path):
        try:
            file = open(file_path, "r")
            content = file.read()
            if any(key in content for key in ["import java", "String[] args", "synchronized", "instanceof", "Boolean", "abstract", "String", "System.out.println"]):
                JavaCommand().execute(file_path)
                return True
        except:
            print("ERROR: File not found or cannot be read.")
            return False
        if self._next_handler:
            return self._next_handler.check_file(file_path)
        return False

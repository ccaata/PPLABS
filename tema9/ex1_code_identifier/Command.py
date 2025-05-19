import subprocess
import os
import shutil


class Command:
    def execute(self, file):
        pass

    def check_tool_installed(self, tool_name):
        return shutil.which(tool_name) is not None


class PythonCommand(Command):
    def execute(self, file):
        if not self.check_tool_installed("python"):
            print("Error: Python is not installed or not in PATH")
            return
        try:
            subprocess.run(["python", file], check=True)
        except subprocess.CalledProcessError as e:
            print(f"Error executing Python file: {e}")


class KotlinCommand(Command):
    def execute(self, file):
        if not self.check_tool_installed("kotlinc"):
            print("Error: Kotlin compiler (kotlinc) is not installed or not in PATH")
            return
        if not self.check_tool_installed("java"):
            print("Error: Java is not installed or not in PATH")
            return

        try:
            # Get the directory of the input file
            file_dir = os.path.dirname(file)
            # Create output jar name in the same directory
            output_jar = os.path.join(file_dir, "cod_kotlin.jar")
            
            # Compile the Kotlin file
            subprocess.run(["kotlinc", file, "-include-runtime", "-d", output_jar], check=True)
            # Run the compiled jar
            subprocess.run(["java", "-jar", output_jar], check=True)
        except subprocess.CalledProcessError as e:
            print(f"Error executing Kotlin file: {e}")


class BashCommand(Command):
    def execute(self, file):
        if not self.check_tool_installed("bash"):
            print("Error: Bash is not installed or not in PATH")
            return
        try:
            subprocess.run(["bash", file], check=True)
        except subprocess.CalledProcessError as e:
            print(f"Error executing Bash file: {e}")


class JavaCommand(Command):
    def execute(self, file):
        if not self.check_tool_installed("java"):
            print("Error: Java is not installed or not in PATH")
            return
        try:
            subprocess.run(["java", file], check=True)
        except subprocess.CalledProcessError as e:
            print(f"Error executing Java file: {e}")

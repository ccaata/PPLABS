from Handlers import HandlePythonFile, HandleKotlinFile, HandleJavaFile, HandleBashFile

if __name__ == '__main__':
    #file_path = "Files/cod_python"
    #file_path = "Files/file.java"
    #file_path = "Files/cod_bash"
    file_path = "Files/cod_kotlin.kt"

    handle1: HandleKotlinFile = HandleKotlinFile()
    handle2: HandlePythonFile = HandlePythonFile()
    handle3: HandleBashFile = HandleBashFile()
    handle4: HandleJavaFile = HandleJavaFile()

    handle1.set_next(handle2)
    handle2.set_next(handle3)
    handle3.set_next(handle4)

    handle1.check_file(file_path)

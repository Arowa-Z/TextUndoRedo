# TextUndoRedo
  This is an android text `undo redo` library.The library is very `small`, only `a Java file`.

# ScreenShot
![demo](image/demo.gif "demo")

# Usage
1. Will [TextUndoRedo.java](library/src/main/java/arowa_z/textundoredo/library/TextUndoRedo.java) copy to your project.

2. To bind TextUndoRedo and EditText.
  ```java
  TUR = new TextUndoRedo(EditText editText, TextChangeInfo info);
  ```
3. call exeUndo() or exeRedo().
  ```java
  TUR.exeUndo();  

  TUR.exeRedo();
  ```

# Note
1. Call `exeUndo()` before please make sure that `canUndo()` is `true`, `exeRedo()` too.

2. You can use the interface `TextChangeInfo` to update `canUndo()` and `canRedo()`.

3. You need to deal with the issue of the screen direction.
  ```xml
  android:configChanges="orientation|keyboardHidden|screenSize"
  ```
  or
  ```xml
  android:screenOrientation="portrait"
  ```
  or
  ```xml
  android:screenOrientation="landscape"
  ```
  or
  ```xml
  ...
  ```
4. For the above these problems, you can consult [sample](sample/src/main) :)

# Author
Name: [Arowa_Z](https://github.com/Arowa-Z)  
Email: Arowa_Z@qq.com

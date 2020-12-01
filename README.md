# PhoneBook

## Software Requirements

Intellij IDEA

MySQL server 8.0




Scenebuilder ( to create FXML UI )

ScenebuilderSetup Instructions :

1. Download and install from [here](https://gluonhq.com/products/scene-builder/thanks/?dl=/download/scene-builder-11-windows-x64/)

2. download [jfoenix-9.0.10](https://jar-download.com/artifacts/com.jfoenix/jfoenix/9.0.10/source-code) and go to library settings in Scenebuilder add import the jar file

2. In intellij go to seetings->languages and frameworks->Set path to installed scenebulider.exe app




External Libraries required : 

  1.[mysql-connector-java-8.0.21](https://dev.mysql.com/downloads/file/?id=498587) ( to connect to MySQL Server )
  
  2.[jfoenix-9.0.10](https://jar-download.com/artifacts/com.jfoenix/jfoenix/9.0.10/source-code) ( to use material components )
  
  4.[openjfx library](https://gluonhq.com/download/javafx-11-0-2-sdk-windows/) ( to support javafx classes )
  
  Go to project settings->libraries and add the all above libraries
  
  
  
  Essential setup to run the project
  
  1.In intellij got to settings->plug-ins-> search for Databases Navigator and install it
  
  2.Download jdk 11 ( [java 11.0.8](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html#license-lightbox) ) and install it,
  
  3.Project SDK level and Module SDK is 11 and project language level is default
  
  4.Download this [openjfx library](https://gluonhq.com/download/javafx-11-0-2-sdk-windows/) jar and extract in "C:\Program Files\Java\"
  
  5.On Intellij go to Run->configurations-> and add VM options as --module-path "C:\Program Files\Java\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls,javafx.fxml
   (we have to manually specify the javafx library path to jvm because javfx is not part of JDK in JDK 11 or above)
   
  6.Click on DB browser and create a MySQL connection,  give the same username and password as in MySQL server and download this [mysql-connector-java-8.0.21]   (https://dev.mysql.com/downloads/file/?id=498587) jar. set driver source as external library and add driver library path to downloaded jar
  

  

@echo off
cd\
cd "Program Files"
cd "Android"
cd "android-sdk"
cd "platform-tools"

set UserInputPackage=com.setes.setesvendas.app
set UserInputDB=pedidovenda 

@echo on
adb shell "run-as %UserInputPackage% chmod 666 /data/data/%UserInputPackage%/databases/%UserInputDB%"
adb pull /data/data/%UserInputPackage%/databases/%UserInputDB% C:\Users\Usuario\Desktop
@echo off

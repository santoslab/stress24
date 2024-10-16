# STRESS 2024 Sireum Installation

* **Linux (amd64)**:

  ```shell
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-linux-amd64.tar.xz https://github.com/sireum/kekinian/releases/download/4.20241015.9f71e14/sireum-ive-linux-amd64.tar.xz
  curl -JLso ~/Downloads/org.sireum.m2.zip https://github.com/sireum/kekinian/releases/download/4.20241015.9f71e14/org.sireum.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-linux-amd64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.m2.zip
  ```

* **macOS (arm64)**:

  ```shell
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-mac-arm64.tar.xz https://github.com/sireum/kekinian/releases/download/4.20241015.9f71e14/sireum-ive-mac-arm64.tar.xz
  curl -JLso ~/Downloads/org.sireum.m2.zip https://github.com/sireum/kekinian/releases/download/4.20241015.9f71e14/org.sireum.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-mac-arm64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.m2.zip
  ```

* **macOS (amd64)**:

  ```shell
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-mac-amd64.tar.xz https://github.com/sireum/kekinian/releases/download/4.20241015.9f71e14/sireum-ive-mac-amd64.tar.xz
  curl -JLso ~/Downloads/org.sireum.m2.zip https://github.com/sireum/kekinian/releases/download/4.20241015.9f71e14/org.sireum.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-mac-amd64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.m2.zip
  ```


* **Windows (amd64)**:

  ```shell
  md %USERPROFILE%\Applications %USERPROFILE%\Downloads 2> nul
  curl -JLso %USERPROFILE%\Downloads\sireum-ive-win-amd64.zip https://github.com/sireum/kekinian/releases/download/4.20241015.9f71e14/sireum-ive-win-amd64.zip
  curl -JLso %USERPROFILE%\Downloads\org.sireum.m2.zip https://github.com/sireum/kekinian/releases/download/4.20241015.9f71e14/org.sireum.m2.zip  
  cd %USERPROFILE%\Applications
  tar xf %USERPROFILE%\Downloads\sireum-ive-win-amd64.zip
  cd %USERPROFILE%
  tar xf %USERPROFILE%\Downloads\org.sireum.m2.zip
  ```
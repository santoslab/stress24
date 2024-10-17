# Sireum for STRESS 2024

## Installation

* **Linux (amd64)**:

  ```shell
  export SIREUM_V=4.20241016.60c1a61
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-linux-amd64.tar.xz https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-ive-linux-amd64.tar.xz
  curl -JLso ~/Downloads/org.sireum.m2.zip https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-linux-amd64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.m2.zip
  ```

* **macOS (arm64)**:

  ```shell
  export SIREUM_V=4.20241016.60c1a61
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-mac-arm64.tar.xz https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-ive-mac-arm64.tar.xz
  curl -JLso ~/Downloads/org.sireum.m2.zip https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-mac-arm64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.m2.zip
  ```

* **macOS (amd64)**:

  ```shell
  export SIREUM_V=4.20241016.60c1a61
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-mac-amd64.tar.xz https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-ive-mac-amd64.tar.xz
  curl -JLso ~/Downloads/org.sireum.m2.zip https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-mac-amd64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.m2.zip
  ```


* **Windows (amd64)**:

  ```shell
  set SIREUM_V=4.20241016.60c1a61
  md %USERPROFILE%\Applications %USERPROFILE%\Downloads 2> nul
  curl -JLso %USERPROFILE%\Downloads\sireum-ive-win-amd64.zip https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/sireum-ive-win-amd64.zip
  curl -JLso %USERPROFILE%\Downloads\org.sireum.m2.zip https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/org.sireum.m2.zip  
  cd %USERPROFILE%\Applications
  tar xf %USERPROFILE%\Downloads\sireum-ive-win-amd64.zip
  cd %USERPROFILE%
  tar xf %USERPROFILE%\Downloads\org.sireum.m2.zip
  ```
  
## Testing Installation

* **macOS/Linux**:

  ```shell
  export SIREUM_HOME=~/Applications/Sireum
  git clone https://github.com/sireum/logika-examples
  logika-examples/bin/verify.cmd
  ```
  
* **Windows**:
  
  ```shell
  set SIREUM_HOME=%USERPROFILE%\Applications\Sireum
  git clone https://github.com/sireum/logika-examples
  logika-examples\bin\verify.cmd
  ```
# Developer-friendly Integrated Coding and Verification with Slang and Logika -- Tool Installation

Below are installation instructions from the Sireum tools that support
the STRESS 2024 lectures "Developer-friendly Integrated Coding and
Verification with Slang and Logika" from Kansas State and Aarhus
Universities.

These steps...
* create folders `~/Applications` and `~/Downloads` (if they already
  exist, the "-p" option in `mkdir` ensures that existing folders will
  be used in subsequent steps).
* download necessary tarballs using `curl`
* install Sireum executables and support files in
  `~/Applications/Sireum` (by tar-extracting `sireum-ive-xxxx`)
* sets and populates up a Maven repository cache in a folder `~/.m2`
  (by tar-extracting `org.sireum.m2.zip`)
  (this ensures that you will be able to build Sireum examples even if
  you have no internet connection (or a slow connection) at the STRESS
  venue)

The Sireum collection of tools contain...
* The Sireum Integrated Verification Environment (IVE) - a customized
  IntelliJ IDE for working with Slang (a safety-critical subset of
  Scala) and Logika (a symbolic execution-based verifier for Slang)
* Slang language infrastructure
* HAMR model-driven development framework
Slang, Logika, and HAMR will be discussed extensively during the lectures.  

## Installation

* **Linux (amd64)**:

  ```shell
  export SIREUM_V=4.20241017.f6e1eff
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-linux-amd64.tar.xz https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-ive-linux-amd64.tar.xz
  curl -JLso ~/Downloads/org.sireum.library.m2.zip https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.library.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-linux-amd64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.library.m2.zip
  ```

* **macOS (arm64)**:

  ```shell
  export SIREUM_V=4.20241017.f6e1eff
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-mac-arm64.tar.xz https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-ive-mac-arm64.tar.xz
  curl -JLso ~/Downloads/org.sireum.library.m2.zip https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.library.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-mac-arm64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.library.m2.zip
  ```

* **macOS (amd64)**:

  ```shell
  export SIREUM_V=4.20241017.f6e1eff
  mkdir -p ~/Applications ~/Downloads
  curl -JLso ~/Downloads/sireum-ive-mac-amd64.tar.xz https://github.com/sireum/kekinian/releases/download/$SIREUM_V/sireum-ive-mac-amd64.tar.xz
  curl -JLso ~/Downloads/org.sireum.library.m2.zip https://github.com/sireum/kekinian/releases/download/$SIREUM_V/org.sireum.library.m2.zip  
  cd ~/Applications
  tar xf ~/Downloads/sireum-ive-mac-amd64.tar.xz
  cd ~
  tar xf ~/Downloads/org.sireum.library.m2.zip
  ```


* **Windows (amd64)**:

  ```shell
  set SIREUM_V=4.20241017.f6e1eff
  md %USERPROFILE%\Applications %USERPROFILE%\Downloads 2> nul
  curl -JLso %USERPROFILE%\Downloads\sireum-ive-win-amd64.zip https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/sireum-ive-win-amd64.zip
  curl -JLso %USERPROFILE%\Downloads\org.sireum.library.m2.zip https://github.com/sireum/kekinian/releases/download/%SIREUM_V%/org.sireum.library.m2.zip  
  cd %USERPROFILE%\Applications
  tar xf %USERPROFILE%\Downloads\sireum-ive-win-amd64.zip
  cd %USERPROFILE%
  tar xf %USERPROFILE%\Downloads\org.sireum.library.m2.zip
  ```
  
## Testing Installation

It will be helpful if you can test your installation.  To do this, you
can retrieve (via git clone) a repository of Logika examples and then
run a script that applies Logika to verify all of the examples (in
batch mode).

* **macOS/Linux**:

  ```shell
  export SIREUM_HOME=~/Applications/Sireum
  git clone https://github.com/sireum/logika-examples
  logika-examples/bin/verify.cmd
  "${SIREUM_HOME}/bin/sireum" proyek ive logika-examples
  ```
  
* **Windows**:
  
  ```shell
  set SIREUM_HOME=%USERPROFILE%\Applications\Sireum
  git clone https://github.com/sireum/logika-examples
  logika-examples\bin\verify.cmd
  "%SIREUM_HOME%\bin\sireum.bat" proyek ive logika-examples
  ```
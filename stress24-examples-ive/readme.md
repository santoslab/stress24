# Waypoint Example Tutorial for Logika

This repository holds a files for a demonstration of Logika/Slang verification
and associated (prototype) testing concepts using the ScalaCheck framework.  The demo
uses examples based on waypoint (coordinate) representation and manipulation
as one might find in mission control applications.

The project structure was derived from the Sireum Proyek example at (see notes in the 
"How This Project Was Created ..." section below, which correspond to steps that you might usually take when
setting up any simple Slang project).  As with all IVE projects that use
Sireum's Proyek build framework, the build structure is configured in
[bin/project.cmd](bin/project.cmd).

Pre-conditions for use:

1. Install Sireum IVE by following the instructions at:
   https://github.com/sireum/kekinian.

2. Add `SIREUM_HOME`'s `bin` directory to the `PATH` environment
   variable:

   * **macOS/Linux:**
   
     ```bash
     export PATH=$PATH:$SIREUM_HOME/bin
     ```
   
   * **Windows**
   
     ```cmd
     set PATH=%PATH%;%SIREUM_HOME%\bin
     ```


## Using Sireum Integrated Verification Environment (IVE)

### Generating Sireum IVE (IntelliJ) Project Meta-Data

Building, running, and verifying Slang programs is supported using the Sireum proyek build system.
Although proyek can work with Slang/Logika from the command line (e.g., compiling, running, invoking Logika verification), this tutorial uses the Sireum IVE (a customization of the IntelliJ IDE) to illustrate concepts.  The Sireum IVE requires IntelliJ project information (meta-data about the project structure and tools) to build, run, and verify Slang projects.  This meta-data is held in a folder named `.idea` (the name is an IntelliJ convention).  We do not check this into git because it contains absolute path information.   Instead, before first opening the project in the Sireum IVE, the meta-data needs to be generated from the proyek building configuration in the `bin/project.cmd` file.

Change directory to the waypoint-tutorial-ive directory (the root directory of the Sireum IVE project, which contains the 
`bin` sub-directory).

Then, use the proyek `ive` utility to generate the IVE project files.  This generates IntelliJ meta-data (in the `.idea` folder) 
about the project based on the Proyek build configuration in `bin/project.cmd`:

```
sireum proyek ive .
```

Then open the `waypoint-tutorial-ive` folder (now a IntelliJ project, based on the presence of the .idea folder) 
in Sireum IVE.

The `.idea` folder is listed in the `.gitignore` file, so it will not be added to your repo files when working with git.   In general, you do not need to run the proyek ive tool before opening the project in the Sireum IVE in the future -- you only need to re-run it if you change the proyek build information in `bin/project.cmd`.

## Overview of Tutorial Structure

### Illustrating Basic Slang/Logika Concepts using Slang Scripts

Basic aspects of Slang and Logika are illustrated using the Slang scripts (special forms of Scala scripts) in the 
[waypoint-scripts](Morning-01/waypoint-scripts) folder.   The scripts start with a starting file and then successively add addition
features.  

To **run a script**, 
first open the file by double-clicking it in the 'Project' view.
(Chose `Ignore` when asked whether to add all Ammonite standard dependencies.)

Then, click on the green ▶ play icon near the top-left corner of the editor.
Alternatively, you can run the script clicking the green ▶ play icon near the
"Slang Script Runner" run configuration on the top middle-right taskbar.

*Note: A script will not produce any output if it has not output statements 
(e.g., `println` or no assertion violations).*

To **verify a script**, open
the file by double-clicking it in the 'Project' view.

Logika will automatically verify the file the first
time the file is opened and after each (paused) modification.

To explicitly re-verify the file, right-click on the opened editor and
select `Logika Check`.

### Illustrating Slang/Logika-based Development in a Sireum Proyek Project

The files in [waypoint-project](waypoint-project) illustrate how Slang and Logika may be used in a "realistic" 
development set up, including compilation, execution, and verification using the Sireum proyek build framework.
This includes illustration of convention unit testing with ScalaTest and property-based (randomized) testing 
using ScalaCheck.

#### Compiling

To compile the source files, use the `Build Project` menu item under
the `Build` menu in Sireum IVE.

#### Running/Debugging

To run the 
[waypoint-project/src/main/scala/waypoint/WaypointApp.scala](waypoint-project/src/main/scala/waypoint/WaypointApp.scala)
program, first open the file by double-clicking it in the `Project` view.

Then, right-click on the file editor and select `Run 'WaypointApp'`/`Debug 'WaypointApp'`.

### Testing/Debugging

To test/debug the test suites in [waypoint-project](waypoint-project), right-click on
[waypoint-project](waypoint-project) in the `Project` view and select
`Run 'ScalaTests in examp...'`/`Debug 'ScalaTests in examp...'`.

### Verifying

To verify files, open
the file by double-clicking it in the 'Project' view.

If 

```// #Sireum #Logika
```

is present on the first line of the file, Logika will automatically verify the file the first
time the file is opened and after each (paused) modification.

To explicitly re-verify the file, right-click on the opened editor and
select `Logika Check`.

# How This Project Was Created from the Sireum Proyek Example

The following steps were taken to set up this project using the Sireum provide proyek-example repo -- a collection of files illustrating how to use Sireum proyek build framework.  You can follow similar steps (e.g., changing the file/folder/project names used) to set up your own proyek-based Slang projects.  This tutorial uses both *.sc Slang script files (to illustrate basic Logika capabilities) as well as complete project (to illustrate Slang/Logika integration into realistic development).  

1. Download the proyek-example repo as a zip file: https://github.com/sireum/proyek-example

2. Unzip and rename top-level folder from `proyek-example-master` to `waypoint-tutorial-ive`.  
   *Note: when creating Slang projects by adapting these instructions, the new name is your choice.  My convention is to add "-ive" to the folder name to identify it as an Slang project that is set up for the Sirem IVE (IntelliJ IDE customization)*.  
   
3. In the top level folder, remove files that are not needed for this project.  This includes the 'example2' folder (which illustrates a how to use a second IntelliJ IDE module) and the *.sc (we will create our own needed Slang script files (*.sc) in a separate folder).

4. Rename the module and source code folders to match the project naming that will be used in the proyek build.

In the top-level folder, rename `example` to `waypointModule`.   This folder holds the Sireum IVE (IntelliJ) module representing a "realistic" development set up.

In the source code folders `src/main/scala` and `src/test/scala`, change the top-level Slang/Scala package name from `example` to `waypoint`.

**What about the contents??**

5. Edit `bin/project.cmd` to setup the proyek build information to match your folder structure (you can compare what is given here with the original `project.cmd` in the proyek-example repo to see what changed)

  - remove module definition for "example2" and remove the reference to "example2" in the "val prj = ..." project declaration.
  - in both the `id` and `basePath` fields, change the module id references from "example" to "waypointModule" to match the name of your module folder
  - Since this tutorial uses the ScalaCheck framework to illustrate advanced unit testing concepts, 
    add dependences for ScalaCheck in bin/project.cmd
     ivyDeps = ISZ("org.sireum.kekinian::library:","org.scalacheck::scalacheck:"),

6. Add the following version information about scalaCheck in a file versions.properties in the top-level folder

   org.scalacheck%%scalacheck%=1.14.1

7. The above steps set up the project to a point where proyek's command line capabilities can be used.  However, in this tutorial, we want to use the Sireum IVE, so we need to create IntelliJ metadata so that the Sireum IVE can understand the project structure and perform builds (the IVE/IntelliJ can't process proyek directly).  So perform the following steps to have proyek generate IntelliJ meta data (this will create a .idea folder at the top level)

  cd to waypoint-tutorial-ive
  $SIREUM_HOME/bin/sireum proyek ive .

8.  After the steps above, you can open the waypoint-tutorial-ive folder in the Sireum IVE and it will recognize it as an IntelliJ project.

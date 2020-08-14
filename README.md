# IMatrix

<p align="center"><a target="_blank"><img width="200"src="https://i.imgur.com/37HGorC.png"></a></p>


An application that allows users to upload images and videos and compress
them to a lower size. :)


# Development
---

All dependancies required to run the project is under <code>pom.xml</code>

External Dependancies include:

    1) Jama(SVD Library)
    2) AWS (AWS S3 Main Storage)
    3) Cloudinary(Alternate Storage)
    4) Java XML Binding API (AWS Dependancy)

Most Java Integrated Development Enviorements such as Eclipse, STS, and Intelji support maven hence all dependancies 
should be automatically installed once imported.

I reccomend seeing our [WIKI](https://github.com/I-Matrix/IMatrix/wiki) for instructions on installation


## Set JAVA_HOME

### Ubuntu/Debian
```sh
    nano ~/.bashrc
```

### Mac OSX

```sh
    cd
    touch .bash_profile
    nano  .bash_profile
```

### Unix
At the end of the bashrc/bash_profile file add
```sh
    export JAVA_HOME=/usr
    export PATH=$JAVA_HOME/bin:$PATH
```

Now to set JAVA_HOME For Windows Systems

### Windows

    1) Go to your environment variables in C-Panel
    2) Create a variable JAVA_HOME and set it to JDK(NOT JRE!) 

[Video going over this](https://www.youtube.com/watch?v=104dNWmM6Rs)

## How to run this Spring aplication

### Ubuntu/Debian
```sh
    ### Type these commands consecutively
    git clone https://github.com/I-Matrix/IMatrix && cd IMatrix
    (cd src && rm -rf test) # Remove all tests for building/deploying purposes
    mvn clean install
    sudo mkdir /root/Downloads # For Cross Compatability with Windows
    (cd target && java -jar imatrix-0.0.1-SNAPSHOT.jar)
```

### Windows

    1) Install Eclipse or Intellij IDE 
    2) Build maven dependancies from within the IDE
    3) Finnaly run maven from within the IDE
    
#### Resources on running Maven 

[Via Eclipse](https://www.youtube.com/watch?v=sNEcpw8LPpo)

[Via Intellij](https://www.youtube.com/watch?v=pt3uB0sd5kY)

[Via Netbeans](https://www.youtube.com/watch?v=CDkdy3BwIqs)

# Algorithm(SVD)

Much more detailed explanation on this algorithm on our [WIKI](https://github.com/I-Matrix/IMatrix/wiki/SVD-Introduction).

Singular Value Decomposition (SVD) states that every (m × n)‑matrix A can be written as a product

<img src="https://i.imgur.com/mj3oj43.png"/>

# FAQ
---

1) My JAVA_HOME isnt set correctly, What can I do? Go [Here](https://github.com/I-Matrix/IMatrix/wiki/Installation---UNIX-Systems)!

2) I got a problem while using IMatrix.me where can I report it? [Here](https://github.com/I-Matrix/IMatrix/issues)!!

3) How do i get to fork this project and work on it myself? Go [Here](https://github.com/I-Matrix/IMatrix/wiki/Installation) for instructions!

4) Are there any other projects you worked on? For sure! Go [here](https://github.com/amanuel2) to see all of them.


# License
---

IMatrix is Licensed under the MIT License.

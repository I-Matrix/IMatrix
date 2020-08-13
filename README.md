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

Most Java Integrated Development Enviorements such as Eclipse, STS, and Intelji support maven hence all dependancies should be automatically installed once imported.

## How to run this Spring aplication

### Ubuntu
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

TODO Abraham

# FAQ
---

None as of now. Will get updated

# License
---

IMatrix is Licensed under the MIT License.

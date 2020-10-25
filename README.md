# Image-searcher

# Overview
The app is developed to have an opportunity to search for images by keyword. Technically, it's a standalone(non distributed) cache of external image storage(secret link) with the possibility to search image urls by metadata.

# How to build 
To build this app you need to have maven 3.8.0 and higher and java 8 and higher.
Command to build in app directory: mvn clean package
Comman to run: java -jar {name}.jar

# Availabe API
GET /image-searcher/search/{keyWord} - search images by key word and return list of their urls

Example: 

request: localhost:8080/image-searcher/search/Pentax 645D

response: 
```js
[
    {
        "croppedPictureUrl": "http://interview.agileengine.com/pictures/cropped/0002.jpg",
        "fullPictureUrl": "http://interview.agileengine.com/pictures/full_size/0002.jpg"
    },
    {
        "croppedPictureUrl": "http://interview.agileengine.com/pictures/cropped/697074.jpg",
        "fullPictureUrl": "http://interview.agileengine.com/pictures/full_size/697074.jpg"
    }
]
```

# Further improvments
1) Adding java docs
2) Adding logs
3) Considering adding project reactor + netty to improve perofmance and scalability
4) Considering adding external cache(e.g redis) to have an ability to launch 1 .. n instances to improve availability and scalability
5) Write unit + integration tests

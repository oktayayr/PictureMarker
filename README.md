#PictureMarker

<img src="art/marker_photo_circle.png"><img src="art/marker_photo_square.png">

PictureMarker provides Marker for Google Map Android API that can contain picture inside it

[ ![Download](https://api.bintray.com/packages/ayar/maven/picture-marker/images/download.svg) ](https://bintray.com/ayar/maven/picture-marker/_latestVersion)

##Features

* PictureMarker is density depended. You can create markers compatible with different device screen
* You can create markers with two different shape: Circle and Square
* You can place image with three different mode: Strech, Center Zoom and Fit

##Attributes

###Dimensions
**All dimension attributes are in density independed. You should set these attributes in dp**

* imageSize: Determines size of image area. If marker style is square, imageSize is the side length of square area. If marker style is circle, imageSize is the diameter of circle area (Default: 25dp)
* borderWidth: Determines width of border (Default: 2dp)
* cornerRadius: Determines radius of corners (Default: 2dp)
* cursorWidth: Determines width of cursor which is at bottom of marker. **It cannot be wider than imageSize** (Default: half of imageSize)
* cursorHeight: Determines height of cursor which is at bottom of marker. (Default: half of imageSize)

###Colors
* borderColor: Determines color of border
* scrapAreaColor: Determines color of scrap area.

**`Scrap area is outside of image in image area. This area appears when given image is not compatible with image area`**

##Setup

###Gradle.build

```groovy
dependencies {
  // jCenter
  compile 'com.oktyayr.picturemarker:picturemarker:1.0.1'
}
```

##Usage

###Create simple marker
```java
  PictureMarker pm = new PictureMarker(image);
  MarkerOptions opt = pm.create(context); // You must provide context object to create marker options
```

###Customize marker
```java
  PictureMarker pm = new PictureMarker(image);
  pm.setMarkerStyle(MarkerStyle.CIRCLE); // Change marker style
  pm.setImageMode(ImageMode.STRECH); // Change image mode
  pm.setImageSize(100) // Change image size (in dp)
  
  MarkerOptions opt = pm.create(context);
```
###Change existing marker options
```java
  MarkerOptions opt=new MarkerOptions().snipper("This is picture marker");
  PictureMarker pm = new PictureMarker(image);
  
  pm.setMarkerStyle(MarkerStyle.CIRCLE); // Change marker style
  pm.setImageMode(ImageMode.STRECH); // Change image mode
  pm.setImageSize(100) // Change image size (in dp)
  
  pm.createInto(context,opt);
```

###Put marker directly into map
```java
  PictureMarker pm = new PictureMarker(image);
  pm.setMarkerStyle(MarkerStyle.CIRCLE); // Change marker style
  pm.setImageMode(ImageMode.STRECH); // Change image mode
  pm.setImageSize(100) // Change image size (in dp)
  
  pm.put(context,map);
```

License
-------

  The MIT License (MIT)
  
  Copyright (c) 2016 Oktay AYAR
  
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  
  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.
  
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.

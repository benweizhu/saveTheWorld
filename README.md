# saveTheWorld

[![Build Status](https://travis-ci.org/benweizhu/saveTheWorld.svg?branch=master)](https://travis-ci.org/benweizhu/saveTheWorld)

./gradlew clean build

cd build/libs

```
java -jar saveTheWorld.jar historyData.txt dcfa0c7a-5855-4ed2-bc8c-4accae8bd155
```
cat1 15 12
cat2 2 3

```
java -jar saveTheWorld.jar historyConflictData.txt dcfa0c7a-5855-4ed2-bc8c-4accae8bd155
```
Conflict found at dcfa0c7a-5855-4ed2-bc8c-4accae8bd155

```
java -jar saveTheWorld.jar historyInvalidFormatData.txt dcfa0c7a-5855-4ed2-bc8c-4accae8bd155
```
Invalid format.
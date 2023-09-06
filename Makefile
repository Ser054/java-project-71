run-dist:
	app/build/install/app/bin/app

setup:
	gradle wrapper --gradle-version 8.3

run:
	./gradlew run

test:
	./gradlew test

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

build:
	./gradlew build
.PHONY: build
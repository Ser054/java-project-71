run-dist:
	app/build/install/app/bin/app

setup:
	./gradlew wrapper --gradle-version 7.4.2

run:
	./gradlew run

test:
	./gradlew test

lint:
	./gradlew checkstyleMain
	./gradlew checkstyleTest

report:
	./gradlew jacocoTestReport

build:
	./gradlew build
.PHONY: build
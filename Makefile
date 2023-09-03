run-dist:
	app/build/install/app/bin/app

setup:
	gradle wrapper --gradle-version 8.3

run:
	./gradlew run

test:
	./gradlew checkstyleTest

lint:
	./gradlew checkstyleMain

build:
	./gradlew build
.PHONY: build
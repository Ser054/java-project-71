run-dist:
	app/build/install/app/bin/app -h

setup:
	./gradlew wrapper

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
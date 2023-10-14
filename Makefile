run-dist:
	app/build/install/app/bin/app -h

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
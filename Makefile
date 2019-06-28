gradle=sh gradlew clean
#This is the name of argument file name
file=src/main/resources/inputFile/input.txt

clean:
	@ $(gradle)

refresh:
	@ ./gradlew --refresh-dependencies

run:
	@ $(gradle) run --args='$(file)'

jar:
	@ $(gradle) jar

test:
	@ $(gradle) test

it:
	@ $(gradle) integrationTest

coverage:
	@ $(gradle) test jacocoTestReport

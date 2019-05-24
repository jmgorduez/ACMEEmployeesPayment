gradle=./gradlew clean
fn=bowling-game.txt

clean:
	@ $(gradle)

refresh:
	@ ./gradlew --refresh-dependencies

cbr:
	@ $(gradle) run --args='$(fn)'

run-app: refresh cbr

jar:
	@ $(gradle) jar

test:
	@ ./gradlew test

ec.com.jmgorduez.ACMEEmployeesPayment.it:
	@ $(gradle) integrationTest

coverage:
	@ $(gradle) test jacocoTestReport

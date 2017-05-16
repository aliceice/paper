# paper

[![Build Status](https://travis-ci.org/aliceice/paper.svg?branch=master)](https://travis-ci.org/aliceice/paper)

Execute the following:

`mvn clean verify -Pcompatibility-report -DoldVersion=$(curl https://oss.sonatype.org/content/repositories/public/de/aliceice/paper/maven-metadata.xml | grep release | sed -E 's/.*<release>(.*)<.*/\1/')`

The report is located under `target/japicmp/japicmp.html`
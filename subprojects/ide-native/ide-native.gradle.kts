/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import gradlebuild.integrationtests.integrationTestUsesSampleDir

plugins {
    id("gradlebuild.distribution.api-java")
}

dependencies {
    implementation(project(":baseServices"))
    implementation(project(":logging"))
    implementation(project(":coreApi"))
    implementation(project(":modelCore"))
    implementation(project(":core"))
    implementation(project(":fileCollections"))
    implementation(project(":dependencyManagement"))
    implementation(project(":ide"))
    implementation(project(":platformBase"))
    implementation(project(":platformNative"))
    implementation(project(":languageNative"))
    implementation(project(":testingBase"))
    implementation(project(":testingNative"))

    implementation(libs.groovy)
    implementation(libs.slf4jApi)
    implementation(libs.guava)
    implementation(libs.commonsLang)
    implementation(libs.inject)
    implementation(libs.plist)

    testImplementation(testFixtures(project(":core")))
    testImplementation(testFixtures(project(":platformNative")))
    testImplementation(testFixtures(project(":languageNative")))
    testImplementation(testFixtures(project(":versionControl")))

    integTestImplementation(project(":native"))
    integTestImplementation(libs.commonsIo)
    integTestImplementation(libs.jgit)

    testFixturesApi(testFixtures(project(":ide")))
    testFixturesImplementation(libs.plist)
    testFixturesImplementation(libs.guava)
    testFixturesImplementation(testFixtures(project(":ide")))

    testRuntimeOnly(project(":distributionsCore")) {
        because("Tests instantiate DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }
    integTestDistributionRuntimeOnly(project(":distributionsNative"))
}

integrationTestUsesSampleDir("subprojects/ide-native/src/main")

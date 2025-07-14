def call() {
    timeout(time: 1, unit: "MINUTES") {
        def qualityGate = waitForQualityGate(abortPipeline: false)
        echo "✅ SonarQube Quality Gate Status: ${qualityGate.status}"

        // Save the result into a file
        writeFile file: 'sonar-quality-gate.txt', text: qualityGate.status

        // Export to env variable so it can be used in email
        env.QUALITY_GATE = qualityGate.status
    }
}

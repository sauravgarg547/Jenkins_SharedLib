def call() {
    timeout(time: 1, unit: "MINUTES") {
        def qualityGate = waitForQualityGate(abortPipeline: false)
        echo "✅ SonarQube Quality Gate Status: ${qualityGate.status}"

        // Save status to file for email reporting
        writeFile file: 'sonar-quality-gate.txt', text: qualityGate.status

        // Export for pipeline-wide usage
        env.QUALITY_GATE = qualityGate.status
    }
}

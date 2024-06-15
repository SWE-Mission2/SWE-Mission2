pipeline {
    agent any
    environment {
        // JUnit 라이브러리 경로 설정 (실제 경로 사용)
        JUNIT_PLATFORM_JAR = 'C:\\Users\\PC\\eclipse\\java-2024-06\\eclipse\\plugins\\junit-platform-console-standalone-1.7.1.jar'
    }
    stages {
        stage('Checkout') {
            steps {
                // 소스 코드 체크아웃
                checkout scm
                // 디렉토리 구조 확인
                bat 'dir'
            }
        }
        stage('Build') {
            steps {
                script {
                    // 일반 클래스 파일 컴파일
                    bat 'javac -encoding UTF-8 -d classes src\\BookManager.java'
                    // 테스트 클래스 파일 컴파일 (JUnit JAR 파일을 클래스패스에 포함)
                    bat "javac -encoding UTF-8 -d test-classes -classpath classes;${env.JUNIT_PLATFORM_JAR} src\\BookManagerTest.java"
                }
            }
        }
        stage('Performance Test') {
            steps {
                script {
                    // 대규모 데이터 추가를 위한 빌드
                    bat 'javac -encoding UTF-8 -d classes src\\PerformanceTest.java'
                    
                    // JUnit 5 테스트 실행을 위한 classpath 설정
                    def classpath = "test-classes;classes;${env.JUNIT_PLATFORM_JAR}"
                    // JUnit 5 테스트 실행
                    bat "java -cp ${classpath} org.junit.platform.console.ConsoleLauncher --select-class PerformanceTest > performance_test_results.txt"
                }
            }
        }
    }
    post {
        always {
            // 테스트 결과 파일을 저장하기 위해 아카이브
            archiveArtifacts artifacts: 'test_results.txt', allowEmptyArchive: true
            archiveArtifacts artifacts: 'performance_test_results.txt', allowEmptyArchive: true
        }
        failure {
            echo 'Build or test failed'
        }
        success {
            echo 'Build and test succeeded'
        }
    }
}

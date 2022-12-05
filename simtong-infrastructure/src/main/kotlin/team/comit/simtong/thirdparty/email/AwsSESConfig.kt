package team.comit.simtong.thirdparty.email

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClient
import org.springframework.cloud.aws.autoconfigure.context.properties.AwsCredentialsProperties
import org.springframework.cloud.aws.autoconfigure.context.properties.AwsRegionProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *
 * AWS SES와 관련된 설정을 관리하는 AwsSESConfig
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/09/08
 * @version 1.0.0
 **/
@Configuration
class AwsSESConfig(
    private val awsCredentialsProperties: AwsCredentialsProperties,
    private val awsRegionProperties: AwsRegionProperties
) {

    @Bean
    fun amazonSimpleEmailService(): AmazonSimpleEmailServiceAsync {
        val basicAWSCredentials = BasicAWSCredentials(
            awsCredentialsProperties.accessKey, awsCredentialsProperties.secretKey
        )

        return AmazonSimpleEmailServiceAsyncClient.asyncBuilder()
            .withCredentials(AWSStaticCredentialsProvider(basicAWSCredentials))
            .withRegion(awsRegionProperties.static)
            .build()
    }
}
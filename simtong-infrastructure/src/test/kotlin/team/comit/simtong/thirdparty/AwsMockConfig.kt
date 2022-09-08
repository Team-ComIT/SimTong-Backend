package team.comit.simtong.thirdparty

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.AnonymousAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import io.findify.s3mock.S3Mock
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class AwsMockConfig {

    private val bucket = "simtong"

    @Bean
    fun s3Mock(): S3Mock {
        return S3Mock.Builder().withPort(8001).withInMemoryBackend().build()
    }

    @Bean(destroyMethod = "shutdown")
    fun amazonS3(s3Mock: S3Mock): AmazonS3 {
        s3Mock.start()
        val endpoint = AwsClientBuilder.EndpointConfiguration("http://127.0.0.1:8001", Regions.AP_NORTHEAST_2.name)

        val client = AmazonS3ClientBuilder
            .standard()
            .withPathStyleAccessEnabled(true)
            .withEndpointConfiguration(endpoint)
            .withCredentials(AWSStaticCredentialsProvider(AnonymousAWSCredentials()))
            .build()
        client.createBucket(bucket)

        return client
    }

}
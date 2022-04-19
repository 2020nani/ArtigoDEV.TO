package com.example.ddd.infrastructure.config.dynamodb;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.example.ddd.core.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfig {

    @Value(("${aws.amazonDynamoDBEndpoint}"))
    private String amazonDynamoDBEndpoint;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, "us-west-2"))
                .build();
        DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withSaveBehavior(DynamoDBMapperConfig.SaveBehavior.CLOBBER)
                .withConsistentReads(DynamoDBMapperConfig.ConsistentReads.CONSISTENT)
                .withTableNameOverride(null)
                .withPaginationLoadingStrategy(DynamoDBMapperConfig.PaginationLoadingStrategy.EAGER_LOADING)
                .build();
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(client, mapperConfig);
        init(dynamoDBMapper, client);
        return dynamoDBMapper;
    }


    public void init(DynamoDBMapper dynamoDBMapper, AmazonDynamoDB client) {

        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Usuario.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

        if (TableUtils.createTableIfNotExists(client, tableRequest)) {
            System.out.println("Table created");
        }

    }

}
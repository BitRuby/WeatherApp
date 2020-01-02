package com.lis.WeatherApp;


import com.lis.WeatherApp.model.Beijing;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

//@EnableBatchProcessing
//@Configuration
public class CsvToMongo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job readCSVFile() {
        return jobBuilderFactory.get("readCSVFile").incrementer(new RunIdIncrementer()).start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step").<Beijing, Beijing>chunk(50).reader(reader())
                .writer(writer()).build();
    }

    @Bean
    public FlatFileItemReader<Beijing> reader() {
        FlatFileItemReader<Beijing> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("static/data2.csv"));
        reader.setLineMapper(new DefaultLineMapper<Beijing>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("No","year","month","day","hour","pm25","DEWP","TEMP","PRES","Iws","Is","Ir");

            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Beijing>() {{
                setTargetType(Beijing.class);
            }});
        }});
        return reader;
    }

    @Bean
    public MongoItemWriter<Beijing> writer() {
        mongoTemplate.dropCollection("beijing");
        MongoItemWriter<Beijing> writer = new MongoItemWriter<Beijing>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("beijing");
        return writer;
    }
}

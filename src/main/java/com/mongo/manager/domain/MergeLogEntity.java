package com.mongo.manager.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "merge_log")
public class MergeLogEntity {

    @Id
    public String id;

    public List<Materials> materials;
}

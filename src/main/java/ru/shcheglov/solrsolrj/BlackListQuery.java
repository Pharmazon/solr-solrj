package ru.shcheglov.solrsolrj;

import lombok.Value;

@Value
public class BlackListQuery {

    String fieldName;

    String fieldValue;
}

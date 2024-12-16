#!/bin/bash

# Elasticsearch가 완전히 시작될 때까지 대기
until curl -s http://localhost:9200 >/dev/null; do
    sleep 5
done

# lawsuit 인덱스 생성
curl -X PUT "http://localhost:9200/lawsuit" -H 'Content-Type: application/json' -d'
{
  "mappings": {
    "properties": {
      "case_number": { "type": "keyword" },
      "court": { "type": "keyword" },
      "date": { "type": "date" },
      "case_type": { "type": "keyword" },
      "judges": { "type": "keyword" },
      "parties": {
        "properties": {
          "plaintiff": {
            "type": "text",
            "fields": {
              "keyword": {
                "type": "keyword",
                "ignore_above": 256
              }
            }
          },
          "defendant": {
            "type": "text",
            "fields": {
              "keyword": {
                "type": "keyword",
                "ignore_above": 256
              }
            }
          }
        }
      },
      "summary": {
        "type": "text",
        "analyzer": "nori"
      },
      "full_text": {
        "type": "text",
        "analyzer": "nori"
      },
      "keywords": { "type": "keyword" },
      "precedents": { "type": "keyword" },
      "ruling": { "type": "keyword" },
      "laws_cited": {
        "type": "text",
        "fields": {
          "keyword": {
            "type": "keyword",
            "ignore_above": 256
          }
        }
      }
    }
  }
}'

echo "Lawsuit index created successfully"

# example_data.json 파일에서 데이터를 읽어 Elasticsearch에 삽입
jq -c '.[]' /usr/share/elasticsearch/sample_data.json | while read -r line; do
    curl -X POST "http://localhost:9200/lawsuit/_doc" -H 'Content-Type: application/json' -d "$line"
done

echo "Example data inserted successfully"

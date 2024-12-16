FROM docker.elastic.co/elasticsearch/elasticsearch:8.7.1

USER root
RUN apt-get update && apt-get install -y jq

# Nori 플러그인 설치
RUN bin/elasticsearch-plugin install --batch analysis-nori

# 인덱스 생성 스크립트 추가
COPY elasticsearch/sample_data.json /usr/share/elasticsearch/sample_data.json
COPY elasticsearch/create_index.sh /usr/local/bin/create_index.sh
RUN chmod +x /usr/local/bin/create_index.sh

# 플러그인 설치 후 권한 조정
RUN chown -R elasticsearch:elasticsearch /usr/share/elasticsearch /usr/local/bin/create_index.sh
USER elasticsearch

# 시작 명령 수정
CMD ["/bin/bash", "-c", "/usr/local/bin/create_index.sh & /usr/local/bin/docker-entrypoint.sh"]
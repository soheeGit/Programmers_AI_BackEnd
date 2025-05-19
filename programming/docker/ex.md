```sh
docker -v
docker run -d -p 8888:80 docker/welcome-to-docker
# http://localhost:8888/
# docker/welcome-to-docker -> 레지스트리 -> 컨테이너
# -d : 백그라운드 실행
# -p : 포트를 연결 -> 앞 : 호스트, 뒤 : 컨테이너
```

```sh
# https://hub.docker.com/_/nginx
# 1.27.5, mainline, 1, 1.27, [latest], 1.27.5-bookworm, mainline-bookworm, 1-bookworm, 1.27-bookworm, bookworm⁠
docker pull nginx
docker images
# docker rmi nginx
```

```sh
docker run nginx # 터미널 백그라운드 실행, 포트 연결
docker run -p 8899:80 nginx # 포트를 지정
# http://localhost:8899/
docker run -d -p 8899:80 nginx
docker ps
# docker run -d -p 8899:80 nginx # 이미 8899가 돌고 있기 때문에 중복해서 포트 사용
docker stop 325a # docker start
docker ps -a
docker tag nginx nginx:v2
docker rm 325a
```
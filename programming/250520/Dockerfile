# base 이미지: nginx
FROM nginx

# index.html을 Nginx 기본 경로로 복사
COPY ./nginx/index.html /usr/share/nginx/html/index.html

# 포트 노출
EXPOSE 80

# nginx 실행
CMD ["nginx", "-g", "daemon off;"]
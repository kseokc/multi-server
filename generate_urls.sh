> urls.txt  # 기존 파일 초기화

for i in {1..100}; do
  for j in {1..3} ; do
      echo "http://localhost:80/post/create?userId=${j} POST {\"title\":\"제목 ${i}\",\"content\":\"내용 ${i}\"}" >> urls.txt
  done
done

# 2. siege 실행
# - c10 -> 동시접속자 10명
# - -r1 -> 1회씩
echo "Starting siege test ..."
siege -c10 -r60 -f urls.txt --header="Content-Type: application/json"

echo "Siege test complete!"
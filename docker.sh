echo "打包开始"
docerfile_name="Dockerfile"
version="latest"
save_name="f:/jars"
save_path="f:/jars/"
echo "正在读取 ${file_name}"
if [ -f "${docerfile_name}" ];then
                docker stop ${file_name}
                docker rm ${file_name}
                docker rmi ${file_name}:${version}
                docker build -t ${file_name}:${version} -f ${docerfile_name} .
                docker save -o ${save_path}${file_name}.tar ${file_name}:${version}
   fi

echo "完成打包"
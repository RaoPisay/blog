#os ubuntu
FROM gradle:jdk21

# Create a user named vscode with /bin/bash as the default shell
#RUN adduser -D -s /bin/bash vscode

#USER vscode

#copy docker_install.bash
COPY docker-install.bash /docker-install.bash

ENTRYPOINT ["docker-install.bash"]
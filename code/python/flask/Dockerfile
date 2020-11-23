FROM python:3.7

WORKDIR /app
# install package
COPY ./requirements.txt requirements.txt
RUN pip install -r requirements.txt

COPY google_cloud_service/ google_cloud_service/
COPY config.py .
COPY app.py .

EXPOSE 5001
ENTRYPOINT ["python"]
CMD ["app.py"]

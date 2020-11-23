import logging
from flask import Flask
from flask_restful import Api
from flask_cors import CORS

from gevent.pywsgi import WSGIServer

from config import Config

# app
app = Flask(__name__)

app.config.from_object(Config)

CORS(app)

api = Api(app)


@app.route("/")
def hello():
    return "Welcome to Flask server"


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO)
    logger = logging.getLogger()

    host = app.config["HOST"]
    port = app.config["PORT"]
    if app.config["USE_WSGI"]:
        logger.info("WSGI Server is running at {host}:{port}".format(host=host, port=port))
        http_server = WSGIServer((host, port), app)
        http_server.serve_forever()
    else:
        logger.info("Flask Server is running at {host}:{port}".format(host=host, port=port))
        app.run(host=host, port=port, debug=True)

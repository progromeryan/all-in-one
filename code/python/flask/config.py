import os

BASE_DIR = os.path.abspath(os.path.dirname(__file__))


class Config(object):
    HOST = os.getenv("HOST", "0.0.0.0")
    PORT = int(os.getenv("PORT", 5001))

    GOOGLE_APPLICATION_CREDENTIALS_KEY = os.getenv("GOOGLE_APPLICATION_CREDENTIALS")

    USE_WSGI = os.getenv("USE_WSGI", "True") == "True"

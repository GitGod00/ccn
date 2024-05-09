import socket

sname = 'localhost'
pn = 8080

try:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
        sock.connect((sname, pn))
        message = 'hi'
        sock.sendall(message.encode('utf-8'))
except Exception as e:
    print(e)
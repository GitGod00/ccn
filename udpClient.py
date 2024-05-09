import socket

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:
    m = 'hi'
    sock.sendto(m.encode('utf-8'), ('localhost', 6000))
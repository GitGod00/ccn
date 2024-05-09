import socket

with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:
    sock.bind(('localhost', 6000))
    print("Listening")
    data, addr = sock.recvfrom(1024)
    print(addr)
    print(data.decode('utf-8'))
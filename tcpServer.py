import socket

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
  sock.bind(('localhost', 8080))
  sock.listen(1)
  client, adr = sock.accept()
  data = client.recv(1024).decode('utf-8')
  print(data)

sock.close()

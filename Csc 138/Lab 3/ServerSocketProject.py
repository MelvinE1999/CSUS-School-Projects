# import socket module
from socket import *

serverSocket = socket(AF_INET, SOCK_STREAM)

# Prepare a sever socket
serverPort = 1240
serverSocket.bind(('127.0.0.1', serverPort))
serverSocket.listen(1)

while True:
    # Establish the connection
    print('Ready to serve...')
    connectionSocket, addr = serverSocket.accept()
    try:
        message = connectionSocket.recv(1024)
        filename = message.split()[1]
        f = open(filename[1:])
        outputData = f.read()

        # Send one HTTP header line into socket
        connectionSocket.send('\nHTTP/1.1 200 OK\n\n'.encode())
        connectionSocket.send(outputData.encode())

        # Send the content of the requested file to the client
        for i in range(0, len(outputData)):
            connectionSocket.send(outputData[i].encode())

        connectionSocket.close()
    except IOError:
        pass
    # Send response message for file not found
        connectionSocket.send('\nHTTP/1.1 404 Not Found\n\n'.encode())

    # Close client socket
        connectionSocket.close()
    break

pass
serverSocket.close()

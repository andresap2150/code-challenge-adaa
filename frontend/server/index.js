const fs = require('fs');
const http = require('http');
const url = require('url');
const port = 3000;

const fileMapping = {
  '/data/1-simple-process': '../data/1-simple-process.json',
  '/data/2-multiple-human-services': '../data/2-multiple-human-services.json',
  '/data/3-branching-process': '../data/3-branching-process.json',
  '/data/4-recursive-branching-process': '../data/4-recursive-branching-process.json'
};

const requestHandler = (request, response) => {
  let requestUrl = url.parse(request.url);
  console.log(requestUrl.path);
  if (fileMapping.hasOwnProperty(requestUrl.path)) {
    response.writeHead(200, {'Content-Type': 'application/json'});
    response.end(fs.readFileSync(fileMapping[requestUrl.path], 'utf8'))
  } else {
    response.writeHead(400, {'Content-Type': 'text/plain'});
    response.end('Invalid Request: ' + request.url, 'utf8')
  }
};

const server = http.createServer(requestHandler);

server.listen(port, (err) => {
  if (err) {
    return console.log('something bad happened', err)
  }

  console.log(`server is listening on ${port}`)
});

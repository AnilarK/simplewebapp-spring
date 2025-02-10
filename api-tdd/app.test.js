const request =require('supertest');
const app = require('./app');

describe( 'Todo api', () => {

    it('GET /todos ->array todos', () => {
       return request(app)
       .get('/todos')
       .expect('Content-Type', /json/)
       .expect(200)
       .then(response=>{
            expect(response.body).toEqual(
                expect.arrayContaining([
                    expect.objectContaining({
                        id: expect.any(Number),
                        name: expect.any(String),
                        completed: expect.any(Boolean)
                    })
                ])
            );
       });
    });

    it('GET /todos/id ->specific todo by id', () => {
        return request(app)
        .get('/todos/1')
        .expect('Content-Type', /json/)
        .expect(200)
        .then(response=>{
             expect(response.body).toEqual(
                     expect.objectContaining({
                         name: expect.any(String),
                         completed: expect.any(Boolean)
                    })
             );
        });
    });

    it('GET /todos/id ->if not found', () => {
       return request(app)
       .get('/todos/9999')
       .expect(404);
    });

    it('POST /todos ->create todos', () => {
        return request(app)
        .post('/todos')
        .send({
            name:'samosa',})
        .expect('Content-Type', /json/)
        .expect(201)
        .then(response=>{
            expect(response.body).toEqual(
                    expect.objectContaining({
                        name: 'samosa',
                        completed: false
                   })
            );
       });
    });

    it('POST /todos -> validates request body', () => {
       return request(app)
       .post('/todos')
       .send({name:234})
       .expect(422);
    });

})
import uvicorn

if __name__ == "__main__":
    uvicorn.run("app.main:app", port=5435, reload=False)

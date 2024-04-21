# Set error action preference to stop the script if any command fails
$ErrorActionPreference = "Stop"

# Build user-directory Docker image
Set-Location "D:\Personal Codebase\project-1\user-directory"
docker build -t user-directory:1.0 .

# Check if the build was successful
if ($?) {
    Write-Host "User-directory Docker image built successfully."
} else {
    Write-Error "Error: User-directory Docker image build failed."
    exit 1
}

# Build address-book Docker image
Set-Location "D:\Personal Codebase\project-1\address-book"
docker build -t address-book:1.0 .

# Check if the build was successful
if ($?) {
    Write-Host "Address-book Docker image built successfully."
} else {
    Write-Error "Error: Address-book Docker image build failed."
    exit 1
}


# Create docker network
Write-Host "Trying to create a network named amathur"
if (-not (docker network inspect amathur)) {
    docker network create amathur
}



# Run user-directory Docker container
docker run --network amathur -d -p 8080:8080 --name user-directory-service user-directory:1.0

# Check if the container is running
if ($?) {
    Write-Host "User-directory Docker container started successfully."
} else {
    Write-Error "Error: Failed to start User-directory Docker container."
    exit 1
}

# Run address-book Docker container
docker run --network amathur -d -p 8081:8081 --name address-book-service address-book:1.0

# Check if the container is running
if ($?) {
    Write-Host "Address-book Docker container started successfully."
} else {
    Write-Error "Error: Failed to start Address-book Docker container."
    exit 1
}

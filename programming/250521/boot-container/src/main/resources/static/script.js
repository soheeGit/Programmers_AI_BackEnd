// DOM Elements
const hamburger = document.querySelector('.hamburger');
const navLinks = document.querySelector('.nav-links');
const playlistCards = document.querySelectorAll('.playlist-card');
const albums = document.querySelectorAll('.album');
const playButton = document.querySelector('.play-btn');
const progressBar = document.querySelector('.progress');
const progressContainer = document.querySelector('.progress-bar');
const volumeSlider = document.querySelector('.volume-slider');
const volumeLevel = document.querySelector('.volume-level');
const currentTimeEl = document.querySelector('.current-time');
const totalTimeEl = document.querySelector('.total-time');

// Mobile Navigation Toggle
hamburger.addEventListener('click', () => {
    hamburger.classList.toggle('active');
    navLinks.classList.toggle('active');
});

// Close mobile menu when clicking on a nav link
document.querySelectorAll('.nav-links a').forEach(link => {
    link.addEventListener('click', () => {
        hamburger.classList.remove('active');
        navLinks.classList.remove('active');
    });
});

// Play/Pause Toggle
let isPlaying = false;
playButton.addEventListener('click', () => {
    isPlaying = !isPlaying;
    if (isPlaying) {
        playButton.innerHTML = '<i class="fas fa-pause"></i>';
        // Here you would normally start playing the audio
    } else {
        playButton.innerHTML = '<i class="fas fa-play"></i>';
        // Here you would normally pause the audio
    }
});

// Progress Bar Interaction
progressContainer.addEventListener('click', (e) => {
    const width = progressContainer.clientWidth;
    const clickX = e.offsetX;
    const percent = (clickX / width) * 100;
    progressBar.style.width = `${percent}%`;

    // Update time display
    const totalSeconds = 210; // 3:30 in seconds
    const currentSeconds = Math.floor((percent / 100) * totalSeconds);
    updateTimeDisplay(currentSeconds, totalSeconds);
});

// Volume Slider Interaction
volumeSlider.addEventListener('click', (e) => {
    const width = volumeSlider.clientWidth;
    const clickX = e.offsetX;
    const percent = (clickX / width) * 100;
    volumeLevel.style.width = `${percent}%`;
    // Here you would normally set the audio volume
});

// Update time display
function updateTimeDisplay(current, total) {
    currentTimeEl.textContent = formatTime(current);
    totalTimeEl.textContent = formatTime(total);
}

// Format time from seconds to MM:SS
function formatTime(seconds) {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = Math.floor(seconds % 60);
    return `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
}

// Add hover effect to playlist cards
playlistCards.forEach(card => {
    card.addEventListener('mouseenter', () => {
        card.querySelector('.play-overlay').style.opacity = '1';
    });

    card.addEventListener('mouseleave', () => {
        card.querySelector('.play-overlay').style.opacity = '0';
    });

    // Simulate click on playlist card
    card.addEventListener('click', () => {
        // Here you would normally load and play the selected playlist
        const playlistTitle = card.querySelector('h3').textContent;
        const nowPlayingTitle = document.querySelector('.track-info h4');
        const nowPlayingArtist = document.querySelector('.track-info p');
        const nowPlayingImage = document.querySelector('.now-playing img');

        nowPlayingTitle.textContent = `Track from ${playlistTitle}`;
        nowPlayingArtist.textContent = 'Various Artists';
        nowPlayingImage.src = card.querySelector('img').src;

        // Auto-play when selecting a new track
        isPlaying = true;
        playButton.innerHTML = '<i class="fas fa-pause"></i>';

        // Reset progress bar
        progressBar.style.width = '0%';
        updateTimeDisplay(0, 210); // Reset to 0:00 / 3:30

        // Animate progress bar
        animateProgress();
    });
});

// Add hover and click effects to albums
albums.forEach(album => {
    album.addEventListener('click', () => {
        // Here you would normally load and play the selected album
        const albumTitle = album.querySelector('h3').textContent;
        const artistName = album.querySelector('p').textContent;
        const nowPlayingTitle = document.querySelector('.track-info h4');
        const nowPlayingArtist = document.querySelector('.track-info p');
        const nowPlayingImage = document.querySelector('.now-playing img');

        nowPlayingTitle.textContent = albumTitle;
        nowPlayingArtist.textContent = artistName;
        nowPlayingImage.src = album.querySelector('img').src;

        // Auto-play when selecting a new track
        isPlaying = true;
        playButton.innerHTML = '<i class="fas fa-pause"></i>';

        // Reset progress bar
        progressBar.style.width = '0%';
        updateTimeDisplay(0, 210); // Reset to 0:00 / 3:30

        // Animate progress bar
        animateProgress();
    });
});

// Simulate progress bar animation
let progressInterval;
function animateProgress() {
    clearInterval(progressInterval);
    let currentWidth = 0;

    progressInterval = setInterval(() => {
        if (isPlaying) {
            currentWidth += 0.1;
            if (currentWidth > 100) {
                currentWidth = 0;
                isPlaying = false;
                playButton.innerHTML = '<i class="fas fa-play"></i>';
                clearInterval(progressInterval);
            }

            progressBar.style.width = `${currentWidth}%`;

            // Update time display
            const totalSeconds = 210; // 3:30 in seconds
            const currentSeconds = Math.floor((currentWidth / 100) * totalSeconds);
            updateTimeDisplay(currentSeconds, totalSeconds);
        }
    }, 100);
}

// Scroll animations for album carousel
const albumCarousel = document.querySelector('.album-carousel');
let isDown = false;
let startX;
let scrollLeft;

albumCarousel.addEventListener('mousedown', (e) => {
    isDown = true;
    albumCarousel.style.cursor = 'grabbing';
    startX = e.pageX - albumCarousel.offsetLeft;
    scrollLeft = albumCarousel.scrollLeft;
});

albumCarousel.addEventListener('mouseleave', () => {
    isDown = false;
    albumCarousel.style.cursor = 'grab';
});

albumCarousel.addEventListener('mouseup', () => {
    isDown = false;
    albumCarousel.style.cursor = 'grab';
});

albumCarousel.addEventListener('mousemove', (e) => {
    if (!isDown) return;
    e.preventDefault();
    const x = e.pageX - albumCarousel.offsetLeft;
    const walk = (x - startX) * 2; // Scroll speed
    albumCarousel.scrollLeft = scrollLeft - walk;
});

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    // Set initial time display
    updateTimeDisplay(105, 210); // 1:45 / 3:30

    // Set cursor style for album carousel
    albumCarousel.style.cursor = 'grab';
});
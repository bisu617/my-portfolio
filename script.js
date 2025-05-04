document.addEventListener("DOMContentLoaded", () => {
    // Progress Bar Animation (Skills Section)
    const observer = new IntersectionObserver((entries) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          entry.target.style.width = entry.target.getAttribute("data-width");
        }
      });
    }, { threshold: 0.5 });
  
    document.querySelectorAll(".progress").forEach(bar => {
      bar.style.width = 0;
      observer.observe(bar);
    });
  
    // Scroll-triggered animations for sections
    const fadeInObserver = new IntersectionObserver((entries, observer) => {
      entries.forEach(entry => {
        if (entry.isIntersecting) {
          entry.target.classList.add("fade-in");
          observer.unobserve(entry.target);
        }
      });
    }, { threshold: 0.5 });
  
    const sectionsToAnimate = document.querySelectorAll("section");
    sectionsToAnimate.forEach(section => fadeInObserver.observe(section));
  
    // Adding Fade-in effect in CSS
    const style = document.createElement("style");
    style.innerHTML = `
      .fade-in {
        opacity: 1;
        transform: translateY(0);
        transition: opacity 1s ease-out, transform 1s ease-out;
      }
      section {
        opacity: 0;
        transform: translateY(50px);
        transition: opacity 0.5s ease-out, transform 0.5s ease-out;
      }
    `;
    document.head.appendChild(style);
  
    // Adding Hover Animation on Project Entries
    const projectEntries = document.querySelectorAll(".project-entry");
    projectEntries.forEach(entry => {
      entry.addEventListener("mouseenter", () => {
        entry.style.transform = "scale(1.05)";
      });
      entry.addEventListener("mouseleave", () => {
        entry.style.transform = "scale(1)";
      });
    });
  
    // Social Media Icon Animation (Scale on Hover)
    const socialIcons = document.querySelectorAll(".social-icon");
    socialIcons.forEach(icon => {
      icon.addEventListener("mouseenter", () => {
        icon.style.transform = "scale(1.2)";
      });
      icon.addEventListener("mouseleave", () => {
        icon.style.transform = "scale(1)";
      });
    });
    const typewriterText = " I enjoy exploring new technologies and working on personal projects to improve my skills!";
    let i = 0;
    
    const typeWriter = () => {
      const paragraph = document.querySelector('.typewriter-text'); // Target the empty paragraph for typewriting
      if (i < typewriterText.length) {
        paragraph.textContent += typewriterText.charAt(i); // Appends one character at a time
        i++;
        setTimeout(typeWriter, 100);
      }
    };
    
    window.onload = () => {
      setTimeout(typeWriter, 1000); // Start the typing effect after a 1-second delay
    };
    
    // Smooth Scrolling (already applied to the document for anchor links)
    const scrollLinks = document.querySelectorAll('a[href^="#"]');
    scrollLinks.forEach(link => {
      link.addEventListener("click", (e) => {
        e.preventDefault();
        const targetId = link.getAttribute("href").substring(1);
        const targetSection = document.getElementById(targetId);
        window.scrollTo({
          top: targetSection.offsetTop - 80, // Adjust the offset if needed
          behavior: "smooth",
        });
      });
    });
  });
  
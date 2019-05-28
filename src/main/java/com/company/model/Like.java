package com.company.model;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Like {
    private LocalDateTime localDateTime;
    private User user;

    public Like(User user) {
        this.localDateTime = LocalDateTime.now();
        this.user = user;
    }

    @Override
    public String toString() {
        return
                "" +
                        user.getUsername() /*+ " liked at " + localDateTime*/
                ;
    }
     
    
    public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return Objects.equals(getUser().getUsername(), like.getUser().getUsername());
    }
}

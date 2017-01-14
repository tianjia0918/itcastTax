package itcast.core.permission;

import itcast.nsfw.user.entity.User;
public interface PermissionCheck {
	
	public abstract Boolean isAccessible(User user, String module);
}

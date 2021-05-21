package passionx3.jkdk.dao;

import java.util.List;

import passionx3.jkdk.domain.Online;

public interface OnlineDao {
	List<Online> getOnlineItemsByKeyword(String keyword);
}
